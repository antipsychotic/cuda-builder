package com.nitrograph.cuda.code.structure

import com.nitrograph.cuda.code._
import scala.collection.immutable.Map
import com.nitrograph.cuda.code.variable._

case class CUDAStructureMember(
    name: String,
    kind: CUDAVariableKind
) extends CUDACode {
    override def toCPP: String = {
        s"${kind.toCPP} ${name}"
    }
}

case class CUDAStructure(
    name: String,
    members: Map[String, CUDAStructureMember]
) extends CUDAVariableKind {
    override def toCPP: String = {
        s"""
            struct ${name} {
                ${members.mapValues(_.toCPP).mkString(";\n")}
            };
        """
    }
}
