package com.maxmind.geoip;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Test(groups = "benchmark")
public class BenchmarkGeoIP
{

    public static final String GEOIP_FILE = "src/main/resources/GeoIP.dat";

    private static final String ipstring[] = { "24.24.24.24", "80.24.24.80",
                    "200.24.24.40", "68.24.24.46" };

    private LookupService cl = null;
    private String type = null;

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        cl = null;
        type = null;
    }

    public void benchmarkMemory() throws Exception
    {
        assertNull(cl);
        cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_MEMORY_CACHE);
        type = "GEOIP_MEMORY_CACHE";

        assertNotNull(cl);
        testGeoipCountry(30000);
        testGeoipRegion(30000);
        testGeoipCity(15000);
    }

    public void benchmarkStandard() throws Exception
    {
        assertNull(cl);
        cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_STANDARD);
        type = "STANDARD";

        assertNotNull(cl);
        testGeoipCountry(3000);
        testGeoipRegion(3000);
        testGeoipCity(1500);
    }

    public void benchmarkCheckCache() throws Exception
    {
        assertNull(cl);
        cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_CHECK_CACHE);
        type = "GEOIP_CHECK_CACHE";
        assertNotNull(cl);
        testGeoipCountry(3000);
        testGeoipRegion(3000);
        testGeoipCity(1500);
    }

    public void benchmarkCheckCacheMemory() throws Exception
    {
        assertNull(cl);
        cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_CHECK_CACHE
                | LookupService.GEOIP_MEMORY_CACHE);
        type = "GEOIP_CHECK_CACHE | GEOIP_MEMORY_CACHE";
        assertNotNull(cl);
        testGeoipCountry(30000);
        testGeoipRegion(30000);
        testGeoipCity(15000);
    }

    public void benchmarkIndexCache() throws Exception
    {
        assertNull(cl);
        cl = new LookupService(GEOIP_FILE, LookupService.GEOIP_INDEX_CACHE);
        type = "GEOIP_INDEX_CACHE";
        assertNotNull(cl);
        testGeoipCountry(2000);
        testGeoipRegion(2000);
        testGeoipCity(1000);
    }

    @SuppressFBWarnings("NP_ALWAYS_NULL")
    private void testGeoipCountry(final int numlookups)
    {

        int i4 = 0;
        long t1 = System.currentTimeMillis();
        for (int i2 = 0; i2 < numlookups; i2++) {
            cl.getCountry(ipstring[i4]);
            i4 = (i4 + 1) % ipstring.length;
        }
        long t2 = System.currentTimeMillis();
        double t3 = ((double) (t2 - t1)) / 1000.0;
        System.out.println("GeoIP Country with " + type);
        System.out.println(numlookups + " lookups made in " + t3 + " seconds ");
    }

    @SuppressFBWarnings("NP_ALWAYS_NULL")
    private void testGeoipRegion(final int numlookups)
    {
        int i4 = 0;
        long t1 = System.currentTimeMillis();
        for (int i2 = 0; i2 < numlookups; i2++) {
            cl.getRegion(ipstring[i4]);
            i4 = (i4 + 1) % ipstring.length;
        }
        long t2 = System.currentTimeMillis();
        double t3 = ((double) (t2 - t1)) / 1000.0;
        System.out.println("GeoIP Region with " + type);
        System.out.println(numlookups + " lookups made in " + t3 + " seconds ");
    }

    @SuppressFBWarnings("NP_ALWAYS_NULL")
    private void testGeoipCity(final int numlookups)
    {
        int i4 = 0;
        long t1 = System.currentTimeMillis();
        for (int i2 = 0; i2 < numlookups; i2++) {
            cl.getLocation(ipstring[i4]);
            i4 = (i4 + 1) % ipstring.length;
        }
        long t2 = System.currentTimeMillis();
        double t3 = ((double) (t2 - t1)) / 1000.0;
        System.out.println("GeoIP City with " + type);
        System.out.println(numlookups + " lookups made in " + t3 + " seconds ");
    }
}
