//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

package com.ourpaths.restclient;

import javax.management.relation.Role;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class contains list of all classes which needs to be run for JUnit
 * testing
 */
@RunWith(Suite.class)
@SuiteClasses(
{ AuthenticatorTest.class, LogEntryTest.class, Role.class})
public class AllTests
{

}
