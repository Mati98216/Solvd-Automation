package com.solvd.laba.testing.mobile;

import com.solvd.laba.mobile.AddContactPage;
import com.solvd.laba.mobile.ContactDetailsPage;
import com.solvd.laba.mobile.ContactsHomePage;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MobileTest extends AbstractTest {


    /**
     * Test to select a contact by its index and initiate a call.
     * This test verifies if the call action can be successfully initiated for a selected contact.
     */
    @Test
    public void testSelectAndCallContact() {
        ContactsHomePage contactsHomePage = new ContactsHomePage(getDriver());
        String name = "John Doe";
        contactsHomePage.selectContactByIndex(name);
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(getDriver());
        contactDetailsPage.clickCall();

        Assert.assertTrue(contactDetailsPage.isCallInitiated(), "Call was not initiated successfully.");
    }
    /**
     * Test to select a contact by its index and add it to favorites.
     * This test verifies if the selected contact can be successfully added to the favorites list.
     */
    @Test
    public void testSelectAndAddToFavoritesContact() {
        ContactsHomePage contactsHomePage = new ContactsHomePage(getDriver());
        String name = "John Doe";
        contactsHomePage.selectContactByIndex(name);
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(getDriver());
        contactDetailsPage.clickAddToFavorites();
        Assert.assertTrue(contactDetailsPage.isContactInFavorites(), "Contact was not added to favorites successfully.");
    }
    /**
     * Test to select a contact by its index, navigate to settings, and delete the contact.
     * This test verifies if the selected contact can be successfully deleted from the contact list.
     */
    @Test
    public void testDeleteContact() {
        ContactsHomePage contactsHomePage = new ContactsHomePage(getDriver());
        String name = "Johna Doe";
        contactsHomePage.selectContactByIndex(name);
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(getDriver());
        contactDetailsPage.clickSettings();
        contactDetailsPage.clickDelete();
        contactDetailsPage.confirmDelete();

        Assert.assertFalse(contactsHomePage.isContactPresent(name), "Contact was not deleted successfully.");
    }
    /**
     * Test to navigate to the add new contact page and create a new contact with provided details.
     * This test verifies if a new contact can be successfully added with the specified information.
     */
    @Test
    public void testAddNewContact() {
        ContactsHomePage contactsHomePage = new ContactsHomePage(getDriver());
        contactsHomePage.clickAddButton();

        AddContactPage addContactPage = new AddContactPage(getDriver());
        String firstName = "Johna";
        String lastName = "Doe";
        String phone = "1234567890";
        String email = "john.doe@example.com";
        addContactPage.createContact(firstName, lastName, phone, email);

        Assert.assertTrue(contactsHomePage.isInContactDetailsPage(), "Not in Contact Details page as expected.");
    }
    /**
     * Test to perform a search operation in the contacts application.
     * This test verifies if the search functionality can correctly find contacts based on the provided query.
     */
    @Test
    public void searchContacts() {
        ContactsHomePage contactsHomePage = new ContactsHomePage(getDriver());
        String query = "d";
        contactsHomePage.clickSearchField(query);
        String name = "John Doe";
        contactsHomePage.selectContactByIndex(name);

        Assert.assertTrue(contactsHomePage.isInContactDetailsPage(), "Not in Contact Details page as expected.");
    }
}