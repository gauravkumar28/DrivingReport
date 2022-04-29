import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TxtFileDrivingDataParsarTest {
    private TxtFileDrivingDataParsar txtFileDrivingDataParsar;
    private File testFile;

    @Before
    public void setup() {
        txtFileDrivingDataParsar = new TxtFileDrivingDataParsar();
    }

    @Test
    public void testDrivingLogEntryCollectionsGeneration() throws FileNotFoundException, InvalidDataException {
        testFile = new File("tst/data/valid_input.txt");
        DrivingLogEntryCollections drivingLogEntryCollections = txtFileDrivingDataParsar.parse(testFile);
        Assert.assertEquals(3, drivingLogEntryCollections.driverList.size());
        Assert.assertEquals(3, drivingLogEntryCollections.tripList.size());
    }

    @Test(expected = FileNotFoundException.class)
    public void testDrivingLogEntryCollectionsGenerationWithWrongFilePath() throws FileNotFoundException, InvalidDataException {
        testFile = new File("tst/data/test_input23.txt");
        DrivingLogEntryCollections drivingLogEntryCollections = txtFileDrivingDataParsar.parse(testFile);
        Assert.assertEquals(3, drivingLogEntryCollections.driverList.size());
        Assert.assertEquals(3, drivingLogEntryCollections.tripList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrivingLogEntryCollectionsGenerationWithInvalidDataFormat() throws FileNotFoundException, InvalidDataException {
        testFile = new File("tst/data/invalid_dataformat_input.txt");
        DrivingLogEntryCollections drivingLogEntryCollections = txtFileDrivingDataParsar.parse(testFile);
        Assert.assertEquals(3, drivingLogEntryCollections.driverList.size());
        Assert.assertEquals(3, drivingLogEntryCollections.tripList.size());
    }

    @Test(expected = InvalidDataException.class)
    public void testDrivingLogEntryCollectionsGenerationWithInvalidTripData() throws FileNotFoundException, InvalidDataException {
        testFile = new File("tst/data/invalid_tripdata_input.txt");
        DrivingLogEntryCollections drivingLogEntryCollections = txtFileDrivingDataParsar.parse(testFile);
        Assert.assertEquals(3, drivingLogEntryCollections.driverList.size());
        Assert.assertEquals(3, drivingLogEntryCollections.tripList.size());
    }
}
