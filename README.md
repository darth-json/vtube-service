# VTube Services

The service component of the v-tube application. 

## Development

To run an individual instance of v-tube locally pull [VTube] and follow the instructions. Video Metadata is extracted via [Sannies-Mp4]. To run, simply execute using
 
 
## Docker Image 

This leverages the spotify docker plugin to build the application as a docker image :
```
mvn clean package docker:build
```

And get the full container stack from [VTube].

## Testing

Unit tests are simple enough, your basic JUnit with a SpringBootRunner abstracted behind a class called `VTubeTest`. To run tests, just run `mvn test`

[VTube]: https://github.com/darth-json/vtube
[VTube-UI]: https://github.com/darth-json/vtube-ui
[Sannies-Mp4]: https://github.com/sannies/mp4parser