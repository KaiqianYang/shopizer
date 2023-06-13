/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL =
            "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.4.2/maven-wrapper-0.4.2.jar";

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH =
            ".mvn/wrapper/maven-wrapper.properties";

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private static final String MAVEN_WRAPPER_JAR_PATH =
            ".mvn/wrapper/maven-wrapper.jar";

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File baseDirectory = /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File(args[0]);
        System.out.println("- Using base directory: " + /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/baseDirectory.getAbsolutePath());

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File mavenWrapperPropertyFile = /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if(/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/mavenWrapperPropertyFile.exists()) {
            /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileInputStream mavenWrapperPropertyFileInputStream = null;
            try {
                mavenWrapperPropertyFileInputStream = new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileInputStream(mavenWrapperPropertyFile);
                Properties mavenWrapperProperties = new Properties();
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream);
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if(mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: : " + url);

        /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File outputFile = /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File(/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH);
        if(!/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*//*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/outputFile.getParentFile().exists()) {
            if(!/*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*//*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output direcrory '" + /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*//*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/File destination) throws Exception {
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileOutputStream fos = new /*~~(TODO ASA-FileStorageApi: need configuration to use storage)~~>*/FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
