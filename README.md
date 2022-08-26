# view-spot-finder

## Usage
To build the project clone the repository and execute `gradle build`.
To use the file, simply execute 

  
      $ java -jar view-spot-finder.jar <path-to-mesh-json> <amount-of-desired-view-spots>

## Data Model

An example of the data model can be found below:
```json
{
  "nodes": [
    {"id": "node_id1", "x": "<number value>", "y": "<number value>"},
    {"id": "node_id2", "x": "<number value>", "y": "<number value>"},
    {"id": "node_id3", "x": "<number value>", "y": "<number value>"},
  ],
  "elements": [
    {"id": "element_id1", "nodes": ["node_id1", "node_id2", "node_id3"]},
  ],
  "values": [
    {"element_id": "element_id1", "value": "<number value>"},
  ]
}
```


