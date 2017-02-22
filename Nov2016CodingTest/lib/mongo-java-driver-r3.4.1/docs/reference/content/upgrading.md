+++
date = "2015-03-19T12:53:39-04:00"
title = "Upgrade Considerations"
[menu.main]
  identifier = "Upgrading to 3.4"
  weight = 80
  pre = "<i class='fa fa-level-up'></i>"
+++

## Upgrading from 3.3.x

There is one breaking API change in the 3.4 release: due to the addition of a new BSON type for 128-bit decimal values, a new method
had to be added to the BSONCallback({{< apiref "org/bson/BSONCallback.html">}}) interface.  Any clients that directly implement that
interface must add an implementation of the new method in order to be compatible with the 3.4 driver.

Otherwise, the 3.4 release is binary and source compatible with the 3.3 release, except for methods that have been added to interfaces that
have been marked as unstable.

## Upgrading from 2.x

You must upgrade first to 3.0 driver.  See the Upgrade guide in the 3.0 driver reference documentation.

### System Requirements

The minimum JVM is now Java 6: however, specific features require Java 7:

- SSL support requires Java 7 in order to perform host name verification, which is enabled by default.  See
[SSL]({{< relref "driver/tutorials/ssl.md" >}}) for details on how to disable host name verification.
- The asynchronous API requires Java 7, as by default it relies on
[`AsynchronousSocketChannel`](http://docs.oracle.com/javase/7/docs/api/java/nio/channels/AsynchronousSocketChannel.html) for
its implementation.  See [Async]({{< ref "driver-async/index.md" >}}) for details on configuring the driver to use [Netty](http://netty.io/) instead.

## Compatibility

The following table specifies the compatibility of the MongoDB Java driver for use with a specific version of MongoDB.

|Java Driver Version|MongoDB 2.6|MongoDB 3.0 |MongoDB 3.2|MongoDB 3.4|
|-------------------|-----------|------------|-----------|-----------|
|Version 3.4        |  ✓  |  ✓  |  ✓  |  ✓  |
|Version 3.3        |  ✓  |  ✓  |  ✓  |     |
|Version 3.2        |  ✓  |  ✓  |  ✓  |     |
|Version 3.1        |  ✓  |  ✓  |     |     |
|Version 3.0        |  ✓  |  ✓  |     |     |

The following table specifies the compatibility of the MongoDB Java driver for use with a specific version of Java.

|Java Driver Version|Java 5 | Java 6 | Java 7 | Java 8 |
|-------------------|-------|--------|--------|--------|
|Version 3.4        |     | ✓ | ✓ | ✓ |
|Version 3.3        |     | ✓ | ✓ | ✓ |
|Version 3.2        |     | ✓ | ✓ | ✓ |
|Version 3.1        |     | ✓ | ✓ | ✓ |
|Version 3.0        |     | ✓ | ✓ | ✓ |
