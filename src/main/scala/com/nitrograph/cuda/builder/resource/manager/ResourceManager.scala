package com.nitrograph.cuda.builder.resource.manager

import com.nitrograph.cuda.builder._

case class ResourceManager(
    HostHeapMemory: Map[String, MemoryChunk[HostRuntimeResource]],
    DeviceMemory: Map[String, MemoryChunk[DeviceRuntimeResource]]
)