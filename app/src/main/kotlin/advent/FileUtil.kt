package advent

/**
 * Created by joey on 3/5/17.
 */
object FileUtil {
    fun readTestResource(obj: Any, resPath: String): String {
        val resource = obj.javaClass.classLoader.getResourceAsStream(resPath) ?: throw IllegalArgumentException("Unable to find test resource $resPath")
        val sb = StringBuilder()
        resource.bufferedReader().forEachLine { sb.append(it) }
        return sb.toString()
    }

    fun resourceToString(path: String): String {
        val resource = ClassLoader.getSystemResourceAsStream(path) ?: throw IllegalArgumentException("Unable to find $path")
        resource.reader().use { return it.readText() }
    }

    fun resourceToList(path: String): List<String> {
        val resource = ClassLoader.getSystemResourceAsStream(path) ?: throw IllegalArgumentException("Unable to find $path")
        return resource.reader().readLines()
    }
}