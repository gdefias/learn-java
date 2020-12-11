/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id$
 */

package xml.demo.castor;

/**
 * Class User.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _userId.
     */
    private String _userId;

    /**
     * Field _userName.
     */
    private String _userName;

    /**
     * Field _password.
     */
    private String _password;

    /**
     * Field _credits.
     */
    private int _credits;

    /**
     * keeps track of state for field: _credits
     */
    private boolean _has_credits;

    /**
     * Field _lastIp.
     */
    private String _lastIp;

    /**
     * Field _logs.
     */
    private Logs _logs;


      //----------------/
     //- Constructors -/
    //----------------/

    public User() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteCredits(
    ) {
        this._has_credits= false;
    }

    /**
     * Returns the value of field 'credits'.
     * 
     * @return the value of field 'Credits'.
     */
    public int getCredits(
    ) {
        return this._credits;
    }

    /**
     * Returns the value of field 'lastIp'.
     * 
     * @return the value of field 'LastIp'.
     */
    public String getLastIp(
    ) {
        return this._lastIp;
    }

    /**
     * Returns the value of field 'logs'.
     * 
     * @return the value of field 'Logs'.
     */
    public Logs getLogs(
    ) {
        return this._logs;
    }

    /**
     * Returns the value of field 'password'.
     * 
     * @return the value of field 'Password'.
     */
    public String getPassword(
    ) {
        return this._password;
    }

    /**
     * Returns the value of field 'userId'.
     * 
     * @return the value of field 'UserId'.
     */
    public String getUserId(
    ) {
        return this._userId;
    }

    /**
     * Returns the value of field 'userName'.
     * 
     * @return the value of field 'UserName'.
     */
    public String getUserName(
    ) {
        return this._userName;
    }

    /**
     * Method hasCredits.
     * 
     * @return true if at least one Credits has been added
     */
    public boolean hasCredits(
    ) {
        return this._has_credits;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'credits'.
     * 
     * @param credits the value of field 'credits'.
     */
    public void setCredits(
            final int credits) {
        this._credits = credits;
        this._has_credits = true;
    }

    /**
     * Sets the value of field 'lastIp'.
     * 
     * @param lastIp the value of field 'lastIp'.
     */
    public void setLastIp(
            final String lastIp) {
        this._lastIp = lastIp;
    }

    /**
     * Sets the value of field 'logs'.
     * 
     * @param logs the value of field 'logs'.
     */
    public void setLogs(
            final Logs logs) {
        this._logs = logs;
    }

    /**
     * Sets the value of field 'password'.
     * 
     * @param password the value of field 'password'.
     */
    public void setPassword(
            final String password) {
        this._password = password;
    }

    /**
     * Sets the value of field 'userId'.
     * 
     * @param userId the value of field 'userId'.
     */
    public void setUserId(
            final String userId) {
        this._userId = userId;
    }

    /**
     * Sets the value of field 'userName'.
     * 
     * @param userName the value of field 'userName'.
     */
    public void setUserName(
            final String userName) {
        this._userName = userName;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled User
     */
    public static User unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (User) org.exolab.castor.xml.Unmarshaller.unmarshal(User.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
