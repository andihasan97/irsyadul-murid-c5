package com.andihasan7.irsyadulmuridc5.Konversi

class Konversi {
    fun getHariPasaran(date: Int, month: Int, year: Int): String {
        val daur = ((year - 1) / 4).toInt() // daur/siklus
        val tahunSisa = ((year - 1) % 4).toInt() // th
        val bulanSisa = month - 1
        val tanggalSisa = date

        val a = daur * 1461
        val b = (tahunSisa * 365).toInt()
        val c =
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {

                if (bulanSisa == 1) {
                    31
                } else if (bulanSisa == 2) {
                    60
                } else if (bulanSisa == 3) {
                    91
                } else if (bulanSisa == 4) {
                    121
                } else if (bulanSisa == 5) {
                    152
                } else if (bulanSisa == 6) {
                    182
                } else if (bulanSisa == 7) {
                    213
                } else if (bulanSisa == 8) {
                    244
                } else if (bulanSisa == 9) {
                    274
                } else if (bulanSisa == 10) {
                    305
                } else if (bulanSisa == 11) {
                    335
                } else if (bulanSisa == 12) {
                    366
                } else {
					0
				}
            } else {
                if (bulanSisa == 1) {
                    31
                } else if (bulanSisa == 2) {
                    59
                } else if (bulanSisa == 3) {
                    90
                } else if (bulanSisa == 4) {
                    120
                } else if (bulanSisa == 5) {
                    151
                } else if (bulanSisa == 6) {
                    181
                } else if (bulanSisa == 7) {
                    212
                } else if (bulanSisa == 8) {
                    243
                } else if (bulanSisa == 9) {
                    273
                } else if (bulanSisa == 10) {
                    304
                } else if (bulanSisa == 11) {
                    334
                } else if (bulanSisa == 12) {
					365
				}else {
                    0
                }
            }
			
			
		val totalS = (a + b + c + tanggalSisa) - 13
		
		val hari = totalS % 7
		
		val hariString = when (hari) {
			1 -> "Sabtu"
			2 -> "Ahad"
			3 -> "Senin"
			4 -> "Selasa"
			5 -> "Rabu"
			6 -> "Kamis"
			7 -> "Jum`at"
			else -> "Jum`at"
		}
		
		val pasaran = totalS % 5
		
		val pasaranString = when (pasaran) {
			1 -> "Kliwon"
			2 -> "Legi"
			3 -> "Pahing"
			4 -> "Pon"
			5 -> "Wage"
			else -> "Wage"
		}
		
		val hasil = hariString + " " + pasaranString
		
		//val test = c
		

        return hasil
		// contoh: Ahad Pahing 
    }
}
