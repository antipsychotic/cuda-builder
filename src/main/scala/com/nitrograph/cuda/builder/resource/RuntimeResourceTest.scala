package com.nitrograph.cuda.builder.resource

import com.nitrograph.cuda.builder.test._
import com.nitrograph.cuda.code._

class RuntimeResourceTest
    extends CUDABuilderTestSuite
{ suite =>
    def simpleProgram: Unit = {
        import com.nitrograph.cuda.code.dsl._

        val cudaProgram = CUDACode.Sequence()

        cudaProgram <+= function("main") { (main, body) =>
            val input = entity("input") of (2048::floats) in ram chunk

            body <+= input.allocate

            val internal = entity("internal") of (2048::floats) in gpu chunk

            body <+= internal.allocate
            body <+= input.copyTo(internal)

            val output = entity("output") of (2048::floats) in ram chunk

            body <+= output.allocate
            body <+= internal.copyTo(output)

            body
        }

        println(cudaProgram.toCPP)
    }

    override def check: Unit = {
        suite.simpleProgram
    }
}
