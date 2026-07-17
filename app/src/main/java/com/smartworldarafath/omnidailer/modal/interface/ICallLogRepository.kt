package com.smartworldarafath.omnidailer.modal.`interface`

import com.smartworldarafath.omnidailer.modal.data.CallLogEntry

interface ICallLogRepository {
    fun getCallLogs(): List<CallLogEntry>
    fun saveCallLog(entry: CallLogEntry)
    fun deleteCallLog(number: String)
    fun deleteCallLogsByIds(ids: List<Long>)
    fun clearCallLogs()
}
