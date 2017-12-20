package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by eunseo on 2017-12-12.
 */

@Root(name="data", strict=false)
public class Data{
    @Attribute(name="error", required = false)
    public int error;

    @Attribute(name="orderno", required = false)
    public String orderno;

    @Attribute(name="payurl" , required = false)
    public String payurl;

    @Attribute(name="statusnm", required = false)
    public String statusnm;
}