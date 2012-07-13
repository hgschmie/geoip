package com.maxmind.geoip;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Test(groups = "require-purchase")
public class OrgLookupTest
{

    public static final String GEO_ORG_FILE = "src/main/resources/GeoIPOrg.dat";
    public static final String GEO_ISP_FILE = "src/main/resources/GeoIPISP.dat";

    public void testOrganization() throws Exception
    {
        LookupService cl = new LookupService(GEO_ORG_FILE, LookupService.GEOIP_MEMORY_CACHE);

        String org1 = cl.getOrg("151.38.39.114");
        String org2 = cl.getOrg("12.25.205.51");
        String org3 = cl.getOrg("64.81.104.131");
        String org4 = cl.getOrg("200.21.225.82");

        assertEquals(org1, "IUnet");
        assertEquals(org2, "AT&T WorldNet Services");
        assertEquals(org3, "Baha in the Rockies dba Wahoo's Fish Taco");
        assertEquals(org4, "COLOMBIA TELECOMUNICACIONES S.A. ESP");

        cl.close();
    }

    public void testISP() throws Exception
    {
        LookupService cl = new LookupService(GEO_ISP_FILE, LookupService.GEOIP_MEMORY_CACHE);

        String isp1 = cl.getOrg("151.38.39.114");
        String isp2 = cl.getOrg("12.25.205.51");
        String isp3 = cl.getOrg("64.81.104.131");
        String isp4 = cl.getOrg("200.21.225.82");

        assertEquals(isp1, "IUnet");
        assertEquals(isp2, "AT&T WorldNet Services");
        assertEquals(isp3, "Speakeasy");
        assertEquals(isp4, "COLOMBIA TELECOMUNICACIONES S.A. ESP");

        cl.close();
    }
}
