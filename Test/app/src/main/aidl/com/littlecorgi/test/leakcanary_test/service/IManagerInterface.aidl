// IManagerInterface.aidl
package com.littlecorgi.test.leakcanary_test.service;

// Declare any non-default types here with import statements
import com.littlecorgi.test.leakcanary_test.service.ICallbackInterface;

interface IManagerInterface {
    void test();
    void setCallback(ICallbackInterface callback);
}
