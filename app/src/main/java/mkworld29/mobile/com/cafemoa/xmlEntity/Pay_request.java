package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by eunseo on 2017-12-12.
 */

@Root(strict = false)
public class Pay_request {
    @Element(name="reshead")
    public ResHead reshead;

    @Element(name="resbody")
    public ResBody resbody;
}