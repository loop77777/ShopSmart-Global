package com.shopsmart.service;

/**
 * E-commerce service demonstrating core Java semantics and service-layer behaviour.
 * This method shows that primitive values are passed by value, while object
 * references still allow mutation of the referenced object state.
 */
public class ECommerceService {
    public void testPassByValueSemantics(int primitiveCount, StringBuilder objectBuilder) {
        primitiveCount = 500; // local copy only; original caller value remains unchanged
        objectBuilder.append(" [Verified Reference Mutation]"); // object mutation is visible to caller
    }
}