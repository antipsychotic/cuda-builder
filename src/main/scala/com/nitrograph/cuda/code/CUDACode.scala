package com.nitrograph.cuda.code

import com.nitrograph.cuda.builder.resource._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.HashMap
import com.nitrograph.cuda.code.structure._

trait CUDACode {
    def toCPP: String
}

object CUDACode {
    def Sequence(
            statements: ArrayBuffer[CUDACode] = ArrayBuffer.empty
        ): StatementSequence = {

        StatementSequence(
            statements
        )

    }

    def Structure(
        name: String,
        members: Map[String, CUDAStructureMember]
    ): CUDAStructure = {
        CUDAStructure(
            name,
            members
        )
    }
}

case class StatementSequence(
    var statements: ArrayBuffer[CUDACode]
) extends CUDACode {
    def <+=(statement: CUDACode): Unit = {
        statements += statement
    }

    def toCPP: String = {
        statements.map(
            _.toCPP
        ).mkString("\n")
    }
}
