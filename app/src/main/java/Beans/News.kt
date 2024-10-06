package Beans

class News(
    var publishedAt: String,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var content: String,
    var source: Source
) {
    constructor(
        publishedAt: String,
        author: String,
        title: String,
        description: String,
        url: String,
        urlToImage: String,
        content: String,
        sourceId: String?,
        sourceName: String
    ) : this(
        publishedAt,
        author,
        title,
        description,
        url,
        urlToImage,
        content,
        Source(sourceId, sourceName)
    )
}

class Source(
    var id: String?,
    var name: String
)