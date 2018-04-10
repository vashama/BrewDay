@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext( { "spring/spring.xml", 
                             "spring/spring-test.xml", 
                             "spring/spring-datasource-test.xml" } )
public class ServicerReportDataUploadDAOImplTest {
    
    Logger log = Logger.getLogger(ServicerReportDataUploadDAOImplTest.class);
    
    SessionContext ctx;
    
    private Connection conn;
    
    private static String FULL_DATASETS_PACK_NO_FORMULA = "xls/Sample Servicer Report Data Upload Sheet_v0.xls";

    @SpringBeanByType
    private ServicerReportDataUploadDAO servicerReportDataUploadDAO;
    
    @SpringBeanByType
    SqlMapClientFactoryBean sqlMapClientFactory;
    //SqlMapClientImpl sqlMapClient;

    DatasetUploadServiceImpl service;
    
    InputStream inputStream;

    HSSFWorkbook workbook;

    @Before
    public void setUp() throws IOException {   
        service = new DatasetUploadServiceImpl();
        service.initializeClassLevelErrorList();
        service.initializeMandatoryAttributesErrorList();
        service.initializeMissingSheetList();
        UserProfile userProfile = new UserProfile();
        String sessionId = "test";
        User user = new User();
        user.setId(7511);
        user.setIdWebSSO("test@db.com");
        userProfile.setUser(user);
        ctx = SessionContext.getSessionContext(sessionId, userProfile);
        final RequestContext requestContext = new RequestContext();
        requestContext.setRequestUser(userProfile.getUser());
        ctx.setRequestContext(requestContext);  
        
        try{
            if(conn==null || conn.isClosed()){
                SqlMapClientImpl impl = (SqlMapClientImpl) sqlMapClientFactory.getObject();
                conn = impl.getDataSource().getConnection();
            }
        } catch(Exception err){
            log.debug(err.getMessage(), err);
        }
    }

   @Ignore("Data changed")
    @Test
    public void testCheckUniqueUploadData() throws DAOException, SQLException, ServiceException {
        //If a DatasetMaster exists in the database, then we get a result of 1, otherwise 0. 
        
        //get all DatasetMaster objects from database 
        List<DataSetMaster> dataSetMaster = this.servicerReportDataUploadDAO.getMasterDetails(); 
        
        Statement stm = conn.createStatement();
        ResultSet rs  = stm.executeQuery(
                "   SELECT " +
                "       dm.DATASET_MASTER_ID dataSetMasterId," +
                "       dm.DATASET_NAME dataSetName, " +
                "       rs.RATING_SYSTEM_NAME ratingSystemName " +
                "   FROM " +
                "       DATASET_MASTER dm, RATING_SYSTEM rs" + 
                "   WHERE " + 
                "       dm.RATING_SYSTEM_ID = rs.RATING_SYSTEM_ID");
        
        while (rs.next()) {
            Long dataSetMasterId = rs.getLong("dataSetMasterId");
            String dataSetName = rs.getString("dataSetName");
            String ratingSystemName = rs.getString("ratingSystemName");
            for (DataSetMaster dm : dataSetMaster) {
                if (dm.getDataSetName().equalsIgnoreCase(dataSetName) && dm.getDataSetMasterId().equals(dataSetMasterId)) {
                    RatingSystem rm = new RatingSystem();
                    rm.setName(ratingSystemName);
                    dm.setRatingSystem(rm);
                }
            }
        }
        
        //get datasetMaster objects (randomly chosen) 
        DataSetMaster dataSetMaster_1 = dataSetMaster.get(8);   //RatingSystem.name == 'Auto Lease'
        DataSetMaster dataSetMaster_2 = dataSetMaster.get(22);  //RatingSystem.name == 'Timeshare Loans'
        
        //non-existing datasetMaster objects
        DataSetMaster dataSetMaster_3 = new DataSetMaster();
        
        Long result_1 = this.servicerReportDataUploadDAO.checkUniqueUploadData(dataSetMaster_1);
        Long result_2 = this.servicerReportDataUploadDAO.checkUniqueUploadData(dataSetMaster_2);
        Long result_3 = this.servicerReportDataUploadDAO.checkUniqueUploadData(dataSetMaster_3);
        
        Long expectedFromDB_1 = 1L;
        Long expectedFromDB_2 = 1L;
        Long expectedFromDB_3 = 0L;
        
        Assert.assertEquals(expectedFromDB_1, result_1);        
        Assert.assertEquals(expectedFromDB_2, result_2);
        Assert.assertEquals(expectedFromDB_3, result_3);
    }
