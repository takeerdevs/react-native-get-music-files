# react-native-get-music-files

## Getting started

`$ npm install react-native-get-music-files --save`

### Mostly automatic installation

`$ react-native link react-native-get-music-files`

## Usage
```javascript
import MusicFiles, { Constants } from "react-native-get-music-files";

MusicFiles.search({ searchParam : 'query string goes here',batchSize:10, batchNumber:0, sortBy:Constants.SortBy.Title,  sortOrder:Constants.SortOrder.Ascending})
 .then(({results, length}) => {
    console.log (`${length} songs found`)
    console.log (`results: ${results}`)
  })
  .catch(er => console.log(er.message));
```
