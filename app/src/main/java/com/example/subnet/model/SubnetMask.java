package com.example.subnet.model;

import android.os.Build;
import androidx.annotation.RequiresApi;

public class SubnetMask extends Address {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getPrefixLength() {
        return (int) toBinaryString().chars().filter(b -> b == '1').count();
    }

    public SubnetMask(int portion1, int portion2, int portion3, int portion4) {
        super(portion1, portion2, portion3, portion4);
    }

    public SubnetMask(int prefixLength) {
        switch (prefixLength) {
            case 1:
                this.portion1 = 128;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 2:
                this.portion1 = 192;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 3:
                this.portion1 = 224;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 4:
                this.portion1 = 240;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 5:
                this.portion1 = 248;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 6:
                this.portion1 = 252;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 7:
                this.portion1 = 254;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 8:
                this.portion1 = 255;
                this.portion2 = 0;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 9:
                this.portion1 = 255;
                this.portion2 = 128;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 10:
                this.portion1 = 255;
                this.portion2 = 192;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 11:
                this.portion1 = 255;
                this.portion2 = 224;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 12:
                this.portion1 = 255;
                this.portion2 = 240;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 13:
                this.portion1 = 255;
                this.portion2 = 248;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 14:
                this.portion1 = 255;
                this.portion2 = 252;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 15:
                this.portion1 = 255;
                this.portion2 = 254;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 16:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 0;
                this.portion4 = 0;
                break;
            case 17:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 128;
                this.portion4 = 0;
                break;
            case 18:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 192;
                this.portion4 = 0;
                break;
            case 19:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 224;
                this.portion4 = 0;
                break;
            case 20:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 240;
                this.portion4 = 0;
                break;
            case 21:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 248;
                this.portion4 = 0;
                break;
            case 22:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 252;
                this.portion4 = 0;
                break;
            case 23:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 254;
                this.portion4 = 0;
                break;
            case 24:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 0;
                break;
            case 25:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 128;
                break;
            case 26:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 192;
                break;
            case 27:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 224;
                break;
            case 28:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 240;
                break;
            case 29:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 248;
                break;
            case 30:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 252;
                break;
            case 31:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 254;
                break;
            case 32:
                this.portion1 = 255;
                this.portion2 = 255;
                this.portion3 = 255;
                this.portion4 = 255;
                break;
        }
    }

    public SubnetMask(String address) {
        super(address);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected SubnetMask clone() {
        return new SubnetMask(getPrefixLength());
    }
}