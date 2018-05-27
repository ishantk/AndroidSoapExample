package google.soapexample;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;
import java.util.List;

public class SoapRequests {

    private static final String MAIN_REQUEST_URL = "https://www.w3schools.com/xml/tempconvert.asmx";



    public String getCelsiusConversion(String fValue) {

        String data = null;
        String methodname = "FahrenheitToCelsius";

        SoapObject request = new SoapObject(MAIN_REQUEST_URL, methodname);
        request.addProperty("Fahrenheit", fValue);

        // Create SOAP Envelope
        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);

        // SOAP Request
        HttpTransportSE ht = getHttpTransportSE();

        try {
            ht.call(MAIN_REQUEST_URL + methodname, envelope);
            SoapPrimitive resultsString = (SoapPrimitive) envelope.getResponse();
            data = resultsString.toString();

        } catch (Exception q) {
            q.printStackTrace();
        }

        return data;
    }

    private SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);
        return envelope;
    }

    private HttpTransportSE getHttpTransportSE() {
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY, MAIN_REQUEST_URL, 60000);
        ht.debug = true;
        ht.setXmlVersionTag("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>");
        return ht;
    }
}