package com.example.subnet.model;

public class IPAddress extends Address {
    private SubnetMask customMask;

    public IPAddress() {
    }

    public IPAddress(String address) {
        super(address);
        this.customMask = getDefaultMask();
    }

    public IPAddress(String address, SubnetMask customMask) {
        super(address);
        this.customMask = customMask;
    }

    public IPAddress(String address, int maskPrefixLength) {
        super(address);
        this.customMask = new SubnetMask(maskPrefixLength);
    }

    public IPAddress(int portion1, int portion2, int portion3, int portion4) {
        super(portion1, portion2, portion3, portion4);
        this.customMask = getDefaultMask();
    }


    public IPAddress(int portion1, int portion2, int portion3, int portion4, SubnetMask customMask) {
        super(portion1, portion2, portion3, portion4);
        this.customMask = customMask;
    }

    public IPAddress(int portion1, int portion2, int portion3, int portion4, int subnetMaskPrefixLength) {
        super(portion1, portion2, portion3, portion4);
        this.customMask = new SubnetMask(subnetMaskPrefixLength);
    }

    public SubnetMask getCustomMask() {
        return customMask;
    }

    public void setCustomMask(SubnetMask customMask) {
        this.customMask = customMask;
    }

    public SubnetMask getDefaultMask() {
        if (portion1 <= 127)
            return new SubnetMask(8);
        else if (portion1 <= 191)
            return new SubnetMask(16);
        else
            return new SubnetMask(24);
    }

    public Character getAddressClass() {
        if (portion1 <= 127)
            return 'A';
        else if (portion1 <= 191)
            return 'B';
        else
            return 'C';
    }

    public Boolean isPrivate() {
        if (portion1 == 10)
            return true;
        else if (portion1 == 172 && (portion2 >= 16 && portion2 <= 31))
            return true;
        else if (portion1 == 192 && portion2 == 168)
            return true;
        else
            return false;
    }

    public Address getNetworkAddress() {
        return new Address(portion1 & customMask.portion1,
                portion2 & customMask.portion2,
                portion3 & customMask.portion3,
                portion4 & customMask.portion4);
    }

    public Address getBroadcastAddress() {
        Address broadcast = getNetworkAddress();
        if (broadcast.portion2 == 0)
            broadcast.setPortion2(255);
        if (broadcast.portion3 == 0)
            broadcast.setPortion3(255);
        if (broadcast.portion4 == 0)
            broadcast.setPortion4(255);
        return broadcast;
    }

    public String getUsableHostIPRange() {
        Address start = getNetworkAddress();
        start.setPortion4(1);
        Address end = getNetworkAddress();
        if (end.portion2 == 0)
            end.setPortion2(255);
        if (end.portion3 == 0)
            end.setPortion3(255);
        if (end.portion4 == 0)
            end.setPortion4(254);
        return start + " - " + end;
    }

    public Integer getTotalNumberOfHosts() {
        return (int) Math.pow(2, customMask.toBinaryString().chars().filter(c -> c == '0').count());
    }

    public Integer getUsableNumberOfHosts() {
        return ((int) Math.pow(2, customMask.toBinaryString().chars().filter(c -> c == '0').count())) - 2;
    }
}