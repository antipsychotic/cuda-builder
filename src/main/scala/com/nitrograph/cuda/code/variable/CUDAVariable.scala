package com.nitrograph.cuda.code.variable

import com.nitrograph.cuda.code._

trait CUDAVariableKind extends CUDACode {
    def toCPP: String
}

sealed trait PrimitiveKind extends CUDAVariableKind

case object FloatKind extends PrimitiveKind {
    override def toCPP: String = {
        s"float"
    }
}

case object DoubleKind extends PrimitiveKind {
    override def toCPP: String = {
        s"double"
    }
}

case object CharKind extends PrimitiveKind {
    override def toCPP: String = {
        s"char"
    }
}

case object BooleanKind extends PrimitiveKind {
    override def toCPP: String = {
        s"boolean"
    }
}

case class CUDAVariable(
    name: String,
    kind: CUDAVariableKind
)
