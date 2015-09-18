This project contains the java library generated for the Dart First State (Delaware)

[API Reference](http://charly1811.github.io/gtfs-java/)

## Note:
The class contains in cf.charleseugene.gtfs.feed are specific to Delaware GTFS feed.
However you can re-create these source codes for any GFTS feed by using the FeedClassesGenerator.java program included.

## How to use FeedClassesGenerator
```
javac FeedClassesGenerator.java
```

```
java FeedClassesGenerator $GTFS_FEED_FOLDER
```

The program will generate the classes for your specific data.

An example of gtfs data is included (__dartfirststate_de_us__). If you will use ```gradlew test``` You *MUST NOT* delete this folder
