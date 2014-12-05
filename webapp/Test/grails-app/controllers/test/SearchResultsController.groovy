
package test
@Grapes([
	@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1'),
	@GrabConfig( systemClassLoader=true )
])

class SearchResultsController {
	def index = { redirect(action: "list", params: params)}

	def list = {
		def http = new HTTPBuilder( 'http://localhost:9090' )

		// perform a GET request, expecting JSON response data
		http.request( GET, JSON ) {
			uri.path = '/solr/collection1/select'
			uri.query = [q: params.query, start: params.start ]

			headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

			// response handler for a success response code:
			response.success = { resp, json ->
				println resp.statusLine

				// parse the JSON response object:
				[results:json.response.docs,
					start:json.response.start,
					query: (params.query + 10)]
			}

			// handler for any failure status code:
			response.failure = { resp -> println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}" }
		}
	}
}