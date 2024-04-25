package megameklab.printing;

import megamek.common.*;

/**
 * Dummy entity that indicates that the page should be broken when printing.
 *
 * @author pavelbraginskiy
 */
public class PageBreak implements BTObject {

    @Override
    public String generalName() {
        return "-PAGE BREAK-";
    }

    @Override
    public String specificName() {
        return "";
    }
}
