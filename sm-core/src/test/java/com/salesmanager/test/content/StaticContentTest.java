package com.salesmanager.test.content;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;



/**
 * Test 
 * 
 * - static content files (.js, .pdf etc)
 * - static content images (jpg, gig ...)
 * @author Carl Samson
 *
 */
@Ignore
public class StaticContentTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	

	@Inject
	private ContentService contentService;
	
	/**
	 * Change this path to an existing image path
	 */
	private final static String IMAGE_FILE = "/Users/carlsamson/Documents/Database.png";
	
	private final static String OUTPUT_FOLDER = "/Users/carlsamson/Documents/test/";
	
	
    @Test
    public void createImage()
        throws ServiceException, /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileNotFoundException, IOException
    {

        MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File file1 = /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File( IMAGE_FILE);

        if ( !/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.exists() || !/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.canRead() )
        {
            throw new ServiceException( "Can't read" + /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.getAbsolutePath() );
        }

        final byte[] is = IOUtils.toByteArray( new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileInputStream( file1 ) );
        final ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        final InputContentFile cmsContentImage = new InputContentFile();
        cmsContentImage.setFileName( /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.getName() );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.IMAGE);
        
        //Add image
        contentService.addContentFile(store.getCode(), cmsContentImage);

    
        //get image
		OutputContentFile image = contentService.getContentFile(store.getCode(), FileContentType.IMAGE, /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.getName());

        //print image
   	 	OutputStream outputStream = new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileOutputStream (OUTPUT_FOLDER + image.getFileName()); 

   	 	ByteArrayOutputStream baos =  image.getFile();
   	 	baos.writeTo(outputStream);
		
		
		//remove image
   	 	contentService.removeFile(store.getCode(), FileContentType.IMAGE, /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/file1.getName());
		


    }
	

}