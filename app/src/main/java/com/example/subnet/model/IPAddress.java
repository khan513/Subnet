package com.example.subnet.model;

public class IPAddress extends Address {
    private SubnetMask customMask;
    private SubnetMask defaultMask;

    public IPAddress() { }

    public IPAddress(String address) {
        super(address);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = defaultMask;
    }

    public IPAddress(String address, SubnetMask customMask) {
        super(address);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = customMask;
    }

    public IPAddress(String address, int maskPrefixLength) {
        super(address);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = new SubnetMask(maskPrefixLength);
    }

    public IPAddress(int portion1, int portion2, int portion3, int portion4) {
        super(portion1, portion2, portion3, portion4);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = defaultMask;
    }


    public IPAddress(int portion1, int portion2, int portion3, int portion4, SubnetMask customMask) {
        super(portion1, portion2, portion3, portion4);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = customMask;
    }

    public IPAddress(int portion1, int portion2, int portion3, int portion4, int subnetMaskPrefixLength) {
        super(portion1, portion2, portion3, portion4);
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
        this.customMask = new SubnetMask(subnetMaskPrefixLength);
    }

    public SubnetMask getCustomMask() {
        return customMask;
    }

    public void setCustomMask(SubnetMask customMask) {
        this.customMask = customMask;
    }

    public void getDefaultMask() {
        if (portion1 <= 127)
            defaultMask = new SubnetMask(8);
        else if (portion1 <= 191)
            defaultMask = new SubnetMask(16);
        else
            defaultMask = new SubnetMask(24);
    }

    public void setDefaultMask(SubnetMask defaultMask) {
        this.defaultMask = defaultMask;
    }

    public Character getAddressClass() {
        int netBitCount = defaultMask.getNetworkBitsCount();
        if (netBitCount > 23)
            return 'C';
        else if (netBitCount > 15)
            return 'B';
        else
            return 'A';
    }

    public Boolean isPrivate() {
        if (portion1 == 10)
            return true;
        else if (portion1 == 172 && (portion2 >= 16 && portion2 <= 31))
            return true;
        else return portion1 == 192 && portion2 == 168;
    }

    public Address getNetworkAddress() {
        return new Address(portion1 & customMask.portion1,
                portion2 & customMask.portion2,
                portion3 & customMask.portion3,
                portion4 & customMask.portion4);
    }

    public Address getFistUsableAddress() {
        return new Address(getNetworkAddress().getNumericValue() + 1);
    }

    public Address getLastUsableAddress() {
        return new Address(getNetworkAddress().getNumericValue() + getTotalNumberOfHosts() - 2);
    }

    public Address getBroadcastAddress() {
        return new Address((getNetworkAddress().getNumericValue() + getTotalNumberOfHosts()) - 1);
    }

    public String getUsableHostIPRange() {
        return getFistUsableAddress() + " - " + getLastUsableAddress();
    }

    public Integer getTotalNumberOfHosts() {
        return (int) Math.pow(2, customMask.toBinaryString().chars().filter(b -> b == '0').count());
    }

    public Integer getUsableNumberOfHosts() {
        return ((int) Math.pow(2, customMask.toBinaryString().chars().filter(b -> b == '0').count())) - 2;
    }

    public Integer getNumberOfSubnets() {
        return ((int) Math.pow(2, customMask.getNetworkBitsCount() - defaultMask.getNetworkBitsCount()));
    }

    public Integer getNumberOfBitsBorrowed() {
        return getCustomMask().getNetworkBitsCount() - defaultMask.getNetworkBitsCount();
    }

    public Address getNthSubnet(Integer n) {
        return new Address((getNetworkAddress().getNumericValue()) + ((long) getTotalNumberOfHosts() * n));
    }
}