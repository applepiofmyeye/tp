package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANNUAL_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANK_ACCOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOIN_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BANKACCOUNT_AMY = "1234567890234";
    public static final String VALID_BANKACCOUNT_BOB = "01823819";
    public static final String VALID_JOINDATE_AMY = "10/05/2003";
    public static final String VALID_JOINDATE_BOB = "20/08/2012";
    public static final String VALID_SALARY_AMY = "4000.00";
    public static final String VALID_SALARY_BOB = "2500.00";
    public static final String VALID_ANNUALLEAVE_AMY = "4";
    public static final String VALID_ANNUALLEAVE_BOB = "2";
    public static final String VALID_ATTENDANCE_TYPE_PRESENT = "present";
    public static final String VALID_ATTENDANCE_TYPE_ABSENT = "absent";
    public static final String VALID_ATTENDANCE_TYPE_LATE = "late";


    public static final ArrayList<String> VALID_ATTENDANCE_STORAGE_AMY = new ArrayList<>();
    public static final ArrayList<String> VALID_ATTENDANCE_STORAGE_BOB = new ArrayList<>();
    public static final String VALID_DATE = "03/11/2023";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_BOB;
    public static final String BANKACCOUNT_DESC_AMY = " " + PREFIX_BANK_ACCOUNT + " " + VALID_BANKACCOUNT_AMY;
    public static final String BANKACCOUNT_DESC_BOB = " " + PREFIX_BANK_ACCOUNT + " " + VALID_BANKACCOUNT_BOB;
    public static final String JOINDATE_DESC_AMY = " " + PREFIX_JOIN_DATE + " " + VALID_JOINDATE_AMY;
    public static final String JOINDATE_DESC_BOB = " " + PREFIX_JOIN_DATE + " " + VALID_JOINDATE_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + " " + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + " " + VALID_SALARY_BOB;
    public static final String ANNUALLEAVE_DESC_AMY = " " + PREFIX_ANNUAL_LEAVE + " " + VALID_ANNUALLEAVE_AMY;
    public static final String ANNUALLEAVE_DESC_BOB = " " + PREFIX_ANNUAL_LEAVE + " " + VALID_ANNUALLEAVE_BOB;
    public static final String ATTENDANCE_TYPE_DESC_ABSENT = " "
            + PREFIX_ATTENDANCE_TYPE + " " + VALID_ATTENDANCE_TYPE_ABSENT;
    public static final String ATTENDANCE_TYPE_DESC_PRESENT = " "
            + PREFIX_ATTENDANCE_TYPE + " " + VALID_ATTENDANCE_TYPE_PRESENT;
    public static final String ATTENDANCE_TYPE_DESC_LATE = " "
            + PREFIX_ATTENDANCE_TYPE + " " + VALID_ATTENDANCE_TYPE_LATE;



    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + " 911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + " bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_BANKACCOUNT_DESC = " " + PREFIX_BANK_ACCOUNT
        + " a91721"; // 'a' not allowed in bankAccount
    public static final String INVALID_JOINDATE_DESC = " " + PREFIX_JOIN_DATE + " 10-923-10"; // wrong date format
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY
        + " a291"; // 'a' not allowed in salary
    public static final String INVALID_ANNUALLEAVE_DESC = " " + PREFIX_ANNUAL_LEAVE
        + " a19"; // 'a' not allowed in annual leave

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditCommand.EditEmployeeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBankAccount(VALID_BANKACCOUNT_AMY).withJoinDate(VALID_JOINDATE_AMY).withSalary(VALID_SALARY_AMY)
                .withAnnualLeave(VALID_ANNUALLEAVE_AMY).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withBankAccount(VALID_BANKACCOUNT_BOB).withJoinDate(VALID_JOINDATE_BOB).withSalary(VALID_SALARY_BOB)
                .withAnnualLeave(VALID_ANNUALLEAVE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
