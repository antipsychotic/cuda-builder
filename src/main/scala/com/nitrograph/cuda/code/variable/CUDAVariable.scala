package com.nitrograph.cuda.code.variable

import com.nitrograph.cuda.code._

trait CUDAVariableKind extends CUDACode {
    def toCPP: String
}

enum PrimitiveKind
    extends CUDAVariableKind
{
    case `Float` {
        override def toCPP: String = {
            s"float"
        }
    }
    case `Double` {
        override def toCPP: String = {
            s"double"
        }
    }
    case `Char` {
        override def toCPP: String = {
            s"char"
        }
    }
    case `Boolean` {
        override def toCPP: String = {
            s"bool"
        }
    }
}

case class CUDAVariable(
    name: String,
    kind: CUDAVariableKind
)
