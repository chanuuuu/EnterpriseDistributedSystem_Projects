package hello

import org.springframework.boot.SpringApplication
import org.springframework.context.annotation._
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

/**
 *
 * @author Chanikya Mandapathi
 * @since 1.0
 */

@EnableAutoConfiguration
object HelloWebApplication {

 def main(args: Array[String]) {
    SpringApplication.run(classOf[HelloConfig]);
 }
}