package seedu.address.model.person.attendance;

/**
 * Types of attendance
 */
public enum AttendanceType {
    LATE,
    ABSENT,
    PRESENT,
    ON_LEAVE;
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance type should only either be 'present', 'late' or 'absent'.";

    /**
     * Validate the attendance type entered by the user.
     * @param name attendance type entered by the user.
     * @return the result of the validation, true if it matches one of the tree attendance types
     */
    public static boolean isValidAttendanceType(String name) {
        name = name.toLowerCase();
        return name.equals("present") || name.equals("absent") || name.equals("late");
    }

}
