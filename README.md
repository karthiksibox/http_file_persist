# file_generator

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

#update config.properties:
    base_url=Server_url #eg: http://host:port
    track_jobs=false (set this to true if you have to track execution details of this project (Tracking requires postgres and you should have run migrations of this project))
    db=job_tracker #ignore if track_jobs is set to false
    user=postgres #ignore if track_jobs is set to false
    password=password #ignore if track_jobs is set to false
    host=localhost #ignore if track_jobs is set to false
    port=5432 #ignore if track_jobs is set to false
    resources=["/it05.json", "/it06.json"] #Endpoints  that has to be serialized to the file
  

 `run lein generate-file` 


# File persist happens according to the configurations in the served json file
### Serving serialization config info: 
{
  #Fields served/needed to be serialized by the service endpoint
    "url_schema": [
        "store_id",
        "price",
        "reason"
    ],
    # formatting info for serialization
    "field_lengths": {
        "store_id": 3,
        "price": 6
          "reason": 100
    },
    #Actual data (url_schema fields with appropriate values)
    "data": {
      "store_id": 12,
      "price": 6
        "reason": "A"
    },
    #(target file)
    "destination_file": "it05.json" 

}




