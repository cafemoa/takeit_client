package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;

/**
 * Created by eunseo on 2017-12-12.
 */

public class Data{
    @Attribute(name="error")
    public int error;

    @Attribute(name="msg")
    public String msg;

    @Attribute(name="orderno")
    public String orderno;

    @Attribute(name="payno")
    public String payno;

    @Attribute(name="paytype")
    public String paytype;

    @Attribute(name="payurl" , required = false)
    public String payurl;

    @Attribute(name="useretc1", required = false)
    public String useretc1;

    @Attribute(name="useretc2", required = false)
    public String useretc2;

    @Attribute(name="useretc3" , required = false)
    public String useretc3;
}