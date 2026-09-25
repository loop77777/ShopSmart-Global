package com.shopsmart.service;


public class ECommerceService {
    public void testPassByValueSemantics(int primitiveCount, StringBuilder objectBuilder) {
        primitiveCount = 500;
        objectBuilder.append(" [Verified Reference Mutation]");
    }
}