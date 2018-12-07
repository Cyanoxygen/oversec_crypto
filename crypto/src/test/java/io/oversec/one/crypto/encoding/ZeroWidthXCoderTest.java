package io.oversec.one.crypto.encoding;

import io.oversec.one.common.CoreContract;
import io.oversec.one.crypto.CoreContractTestBase;
import io.oversec.one.crypto.proto.Outer;

import org.junit.Before;
import org.junit.Test;

public class ZeroWidthXCoderTest extends EncodingTestBase {

    @Before
    public void setUp() throws Exception {
        CoreContract.init(new CoreContractTestBase() {
            @Override
            public boolean isDbSpreadInvisibleEncoding(String packagename) {
                return true;
            }
        });
        super.setUp();
    }

    @Override
    AbstractXCoder createCoder() {
        return new ZeroWidthXCoder(mContext);
    }

    @Test
    public void testEncodeDecodeGpg() throws Exception {
        Outer.Msg msgIn = createTestOuterMsgPgp();
        String encoded = mCoder.encodeInternal(msgIn,null,"foo.bar");
        Outer.Msg msgOut = mCoder.decode(encoded);
        assertEquals(msgIn, msgOut);
    }


    @Test
    public void testEncodeDecodeGpgWithDecoyText() throws Exception {
        Outer.Msg msgIn = createTestOuterMsgPgp();
        String encoded = mCoder.encodeInternal(msgIn,null,"foo.bar") + "DecoyDecoyDecoy";
        Outer.Msg msgOut = mCoder.decode(encoded);
        assertEquals(msgIn, msgOut);
    }

    @Test
    public void testEncodeDecodeSym() throws Exception {
        Outer.Msg msgIn = createTestOuterMsgSym();
        String encoded = mCoder.encodeInternal(msgIn,null,"foo.bar");
        Outer.Msg msgOut = mCoder.decode(encoded);
        assertEquals(msgIn, msgOut);
    }

    @Test
    public void testEncodeDecodeSymWithDecoyText() throws Exception {
        Outer.Msg msgIn = createTestOuterMsgSym();
        String encoded = mCoder.encodeInternal(msgIn,null,"foo.bar") + "DecoyDecoyDecoy";
        Outer.Msg msgOut = mCoder.decode(encoded);
        assertEquals(msgIn, msgOut);
    }
}