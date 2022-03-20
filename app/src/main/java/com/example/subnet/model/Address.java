package com.example.subnet.model;

public class Address {
    protected Integer portion1;
    protected Integer portion2;
    protected Integer portion3;
    protected Integer portion4;

    public Address() {

    }

    public Address(Integer portion1, Integer portion2, Integer portion3, Integer portion4) {
        this.portion1 = portion1;
        this.portion2 = portion2;
        this.portion3 = portion3;
        this.portion4 = portion4;
    }

    public Address(Long numericValue) {
        this.portion1 = (int) (((numericValue / 256L) / 256) / 256);
        this.portion2 = (int) (((numericValue - (this.portion1 * 256L * 256 * 256)) / 256) / 256);
        this.portion3 = (int) (((numericValue - (this.portion1 * 256L * 256 * 256)) - (this.portion2 * 256L * 256)) / 256);
        this.portion4 = (int) (((((numericValue - (this.portion1 * 256L * 256 * 256)) - (this.portion2 * 256L * 256))) - this.portion3 * 256));
    }

    public Address(String address) {
        this.portion1 = Integer.parseInt(address.substring(0, address.indexOf('.')));
        this.portion2 = Integer.parseInt(address.substring(address.indexOf('.') + 1, address.indexOf('.', address.indexOf('.') + 1)));
        this.portion3 = Integer.parseInt(address.substring(address.indexOf('.', address.indexOf('.') + 1) + 1, address.lastIndexOf('.')));
        this.portion4 = Integer.parseInt(address.substring(address.lastIndexOf('.') + 1));
    }

    public int getPortion1() {
        return portion1;
    }

    public void setPortion1(int portion1) {
        this.portion1 = portion1;
    }

    public int getPortion2() {
        return portion2;
    }

    public void setPortion2(int portion2) {
        this.portion2 = portion2;
    }

    public int getPortion3() {
        return portion3;
    }

    public void setPortion3(int portion3) {
        this.portion3 = portion3;
    }

    public int getPortion4() {
        return portion4;
    }

    public void setPortion4(int portion4) {
        this.portion4 = portion4;
    }

    public String getOctet1() {
        return portionToOctet(portion1);
    }

    public String getOctet2() {
        return portionToOctet(portion2);
    }

    public String getOctet3() {
        return portionToOctet(portion3);
    }

    public String getOctet4() {
        return portionToOctet(portion4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address a = (Address) o;
        return portion1 == a.portion1 && portion2 == a.portion2 && portion3 == a.portion3 && portion4 == a.portion4;
    }

    @Override
    public String toString() {
        return portion1 + "." + portion2 + "." + portion3 + "." + portion4;
    }

    public String toBinaryString() {
        return getOctet1() + "." + getOctet2() + "." + getOctet3() + "." + getOctet4();
    }

    protected static String portionToOctet(int portion) {
        StringBuilder octet = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            int k = portion >> i;
            if ((k & 1) > 0)
                octet.append("1");
            else
                octet.append("0");
        }
        return octet.substring(octet.length() - 8, octet.length());
    }

    public Long getNumericValue() {
        return (portion1 * 256L * 256 * 256) + (portion2 * 256L * 256) + (portion3 * 256L) + portion4;
    }
}