package com.maxmind.geoip;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "test")
public class CityLookupTest
{
    public static final String GEOIP_FILE = "src/main/resources/GeoLiteCity.dat";

    public void testCityLookup1() throws Exception
    {
        LookupService cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE );

        Location l1 = cl.getLocation("213.52.50.8");

        assertEquals(l1.countryCode, "NO");
        assertEquals(l1.countryName, "Norway");
        assertEquals(l1.region, "09");
        assertEquals(RegionName.regionNameByCode(l1.countryCode, l1.region), "Nordland");
        assertEquals(l1.city, "Bodø");
        Assert.assertEquals(l1.latitude, 67.283295f, 0.01f);
        assertEquals(l1.longitude, 14.383301f, 0.01f);
        assertEquals(l1.metro_code, 0);
        assertEquals(l1.area_code, 0);
        assertEquals(TimeZone.timeZoneByCountryAndRegion(l1.countryCode, l1.region), "Europe/Oslo");

        cl.close();
    }

    public void testCityLookup2() throws Exception
    {
        LookupService cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE );

        Location l2 = cl.getLocation("64.4.4.4");

        assertEquals(l2.countryCode, "US");
        assertEquals(l2.countryName, "United States");
        assertEquals(l2.region, null);
        assertEquals(RegionName.regionNameByCode(l2.countryCode, l2.region), null);
        assertEquals(l2.city, null);
        assertEquals(l2.latitude, 38.0f, 0.01f);
        assertEquals(l2.longitude, -97.0f, 0.01f);
        assertEquals(l2.metro_code, 0);
        assertEquals(l2.area_code, 0);
        assertEquals(TimeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region), null);
    }
}
