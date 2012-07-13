package com.maxmind.geoip;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Test(groups = "require-purchase")
public class RegionLookupTest
{

    public static final String GEOIP_FILE = "src/main/resources/GeoIPRegion.dat";

    public void testRegion() throws Exception
    {
        LookupService cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE);

        Region r1 = cl.getRegion("151.38.39.114");
        Region r2 = cl.getRegion("12.25.205.51");
        Region r3 = cl.getRegion("64.81.104.131");
        Region r4 = cl.getRegion("200.21.225.82");

        assertEquals(r1.countryCode, "IT");
        assertEquals(r1.countryName, "Italy");
        assertEquals(r1.region, "20");
        assertEquals(RegionName.regionNameByCode(r1.countryCode, r1.region), "Veneto");

        assertEquals(r2.countryCode, "US");
        assertEquals(r2.countryName, "United States");
        assertEquals(r2.region, null);
        assertEquals(RegionName.regionNameByCode(r2.countryCode, r2.region), null);

        assertEquals(r3.countryCode, "US");
        assertEquals(r3.countryName, "United States");
        assertEquals(r3.region, "CO");
        assertEquals(RegionName.regionNameByCode(r3.countryCode, r3.region), "Colorado");

        assertEquals(r4.countryCode, "CO");
        assertEquals(r4.countryName, "Colombia");
        assertEquals(r4.region, "26");
        assertEquals(RegionName.regionNameByCode(r4.countryCode, r4.region), "Santander");

        cl.close();
    }
}
