import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

File inputFile = new File('/tmp/openxc-input.json')
String[] lines = inputFile.text.split('\n')

def slurper = new JsonSlurper()
def jsonBuilder = new JsonBuilder();
    
def odom
def lat
def lng
def fuel
def tm
def timestamp
def lngFound = false
def latFound = false

//def url = new URL('http://localhost:9000')
def url = new URL('http://dataflow-balloonlike-demonolatry-vehicle-data-stream-http.pcf8.cloud.fe.pivotal.io')


lines.each { line ->
    def jsonPayload = slurper.parseText(line)

    tm = jsonPayload?.timestamp
        
    if (jsonPayload?.name == 'fuel_level') {
        fuel = jsonPayload.value
    }
    else if (jsonPayload?.name == 'odometer') {
        odom = jsonPayload.value
    }
    else if (jsonPayload?.name == 'latitude') {
        lat = jsonPayload.value
        latFound = true;
    }
    else if (jsonPayload?.name == 'longitude') {
        lng = jsonPayload.value
        lngFound = true;
    }
    
    if (latFound && lngFound) {
        jsonBuilder() {
            odometer odom
            fuel_level fuel
            latitude lat
            longitude lng
            time tm
        }

        HttpURLConnection connection = url.openConnection()
        connection.setRequestMethod("POST")
        connection.setDoOutput(true)
        def writer = new OutputStreamWriter(connection.outputStream)
        writer.write(jsonBuilder.toString())
        writer.flush()
        writer.close()
                   
        InputStream response = connection.getInputStream();                   
        
        println jsonBuilder.toString()
        
        latFound = false
        lngFound = false
    }
}

return jsonBuilder.content
