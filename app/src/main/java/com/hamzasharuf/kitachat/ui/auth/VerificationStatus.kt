package com.hamzasharuf.kitachat.ui.auth

import java.lang.Exception

sealed class VerificationStatus {
    object Processing: VerificationStatus()
    object Pending: VerificationStatus()
    object Success: VerificationStatus()
    data class Failed(val exception: Exception): VerificationStatus()
}