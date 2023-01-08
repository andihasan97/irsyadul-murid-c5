package com.andihasan7.irsyadulmuridc5.IrsyadulMurid

import java.util.*
import kotlin.math.*

class IrsyadulMurid(var date: Int, var month: Int, var year: Int) {

    public var elevation = 150
    public var timeZone = 7
    public var lintangTempat = -7.4333333333337
    public var bujurTempat = 111.4333333333337
    public var ihthiyat = (2.0 / 60)
	
	
	
	

    fun convertToTime(decimal: Double): String {
    	var time = decimal.toInt().toString()
    	var minute = ((decimal - time.toDouble()) * 60).toInt().toString()
    	var second = (((decimal - time.toDouble()) * 60 - minute.toDouble()) * 60).toInt().toString()

    	// Tambahkan perhitungan untuk membulatkan detik ke menit
    	if (second.toDouble() <= 60 && second.toDouble() >= 30) {
        	minute = (minute.toInt() + 1).toString()
            second = (second.toInt() - 60).toString()
    	}
        
        // Tambahkan nol sebelum angka yang kurang dari 10
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')

    	return "$time : $minute"
	}
	
	fun convertToTimeL(decimal: Double): String {
    	var time = decimal.toInt().toString()
    	var minute = ((decimal - time.toDouble()) * 60).toInt().toString()
    	var second = (((decimal - time.toDouble()) * 60 - minute.toDouble()) * 60).round(2).toString()
        
        time = time.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')

    	return "$time : $minute : $second"
	}
	
	fun convertToDegree(decimal: Double): String {
    	var degree = abs(decimal).toInt().toString()
    	var minute = ((abs(decimal) - degree.toDouble()) * 60).toInt().toString()
    	var second = ((((abs(decimal) - degree.toDouble()) * 60) - minute.toDouble()) * 60).round(2).toString()

        // Tambahkan nol sebelum angka yang kurang dari 10
        degree = degree.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')
        
        if (decimal < 0) {
            degree = "-$degree"
            minute = "-$minute"
            second = "-$second"
        }

    	return "$degree° $minute` $second``"
	}
	
	fun Double.round( decimals : Int ) : Double {
		var multiplier = 1.0
		repeat ( decimals ) { multiplier *= 10 }
		return round ( this * multiplier ) / multiplier 
	}
	
	

	
	
	// koreksi bulan & tahun
	var bulan = if (month < 3) {
			month + 12
		} else {
			month
		}
	
	var tahun = if (month < 3) {
			year - 1
		} else {
			year
		}
	
	
		
		
	
		
	// koreksi gergorius
	val krg = (2 - (tahun.toDouble() / 100) + ((tahun.toDouble() / 100) / 4)).toInt()
	
	// julian day
	val jd = ((365.25 * (tahun + 4716)).toInt() + (30.6001 * (bulan + 1)).toInt() + date + (((10 + 23 / 60.0) / 24) + krg) - 1524.5).round(3)

	
	// juz ashal miladiyah
	val jam = ((jd - 2451545) / 36525).round(9)
	
	/* 
		mulai menghitung data matahari
	*/
	
	// wasatus syamsi
	val s = 280.46645 + 36000.76983 * jam
	val frS = (s / 360 - (s / 360).toInt()) * 360
	
	// khooshotus syamsi
	val m = 357.52910 + 35999.05030 * jam
    val frm = (m / 360 - (m / 360).toInt()) * 360
	
	// 'uqdatus syams
	val n = 125.04 - 1934.136 * jam
    val frN = (n / 360 - (n / 360).toInt()) * 360
	
	// tahshishul awwal/ koreksi pertama
	val k1 = (17.264 / 3600) * sin(Math.toRadians(frN)) + (0.206 / 3600) * sin(Math.toRadians(2 * frN))
	
	// tahshishus tsani/ koreksi kedua
    val k2 = (-1.264 / 3600) * sin(Math.toRadians(2 * frS))
	
	// tahshishus tsaalist/ koreksi ketiga
    val r = (9.23 / 3600) * cos(Math.toRadians(frN)) - (0.090 / 3600) * cos(Math.toRadians(2 * frN))
	
	// tahshishur roobi'/ koreksi keempat
	val r1 = (0.548 / 3600) * cos(Math.toRadians(2 * frS))
	
	// mail kulli
    val q = 23.43929111 + r + r1 - (46.8150 / 3600) * jam
	
	// ta'diilus syams
    val e = (6898.06 / 3600) * sin(Math.toRadians(frm)) +  (72.095 / 3600) * sin(Math.toRadians(2 * frm)) + (0.966 / 3600) * sin(Math.toRadians(3 * frm))
	
	// thuulus syams
    val s1 = frS + e + k1 + k2 - (20.47 / 3600)
	
	// mail syams/ deklinasi matahari
    val dek = Math.toDegrees(asin(sin(Math.toRadians(s1)) * sin(Math.toRadians(q))))
	
	// ta'diluz zaman/ equation of time
    val eq = (-1.915 * sin(Math.toRadians(frm)) + (-0.02) * sin(Math.toRadians(2 * frm)) + 2.466 * sin(Math.toRadians(2 * s1)) + (-0.053) * sin(Math.toRadians(4 * s1))) / 15
	
	// semidiameter
	val sd = 0.267 / (1 - 0.017 * cos(Math.toRadians(frm)))
	
	
	/* 
		proses hitung
	*/
	
	// Dzuhur
    val dzuhurWib = 12 - eq + ((timeZone * 15) - bujurTempat) / 15 + ihthiyat
	
	// Ashar
    val hAshar = Math.toDegrees(atan(1.0 / (tan(Math.toRadians(abs(lintangTempat - dek))) + 1)))
	val f = -tan(Math.toRadians(lintangTempat)) * tan(Math.toRadians(dek))
	val gE = cos(Math.toRadians(lintangTempat)) * cos(Math.toRadians(dek))
	val aS = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hAshar)) / gE)) / 15 + ihthiyat // Ashar Istiwa'
	val asharWib = aS - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Maghrib
    val dip = (1.76 / 60) * Math.sqrt((elevation).toDouble()) // konversi ke Double disini untuk memudahkan suatu saat custom tinggi tempat
    // ho Maghrib
    val hM = -(sd + (34.5 / 60) + dip) - 0.0024
    // Maghrib WIS
    val tM = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hM)) / gE)) / 15 + ihthiyat
    // Maghrib WIB
    val maghribWib = tM - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Isya'
    val hI = -18.0
    // Isya' WIS
    val iS = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hI)) / gE)) / 15 + ihthiyat 
	// Isya' WIB
    val isyaWib = iS - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// h Shubuh
    val hS = -20.0
    // Shubuh WIS
    val sI = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hS)) / gE)) / 15 + ihthiyat
    // Shubuh WIB
    val shubuhWib = sI - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Imsak
    val imsakWib = shubuhWib - (10.0 / 60)
	
	// h Thulu'
    val hT = -(sd + (34.5 / 60) + dip) - 0.0024
    // Thulu' WIS
    val tI = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hT)) / gE)) / 15 - ihthiyat // Ihthiyat untuk Thulu' dikurangi 2 menit
    // Thulu' WIB
    val thuluWib = tI - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Dluha
    val hD = 4.5
    // Dluha WIS
    val dL = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hD)) / gE)) / 15 + ihthiyat
    // Dluha WIB
    val dluhaWib = dL - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Nishful Lail
    val tengahMalamWib = maghribWib + ((shubuhWib + 24 - maghribWib) / 2) - ihthiyat // dikurangi ihthiyat karena sudah mengambil data maghrib dan shubuh yang sudah ditambah ihthiyat
	
	// Arah Qiblat
    // lintang dan bujur Ka'bah'
    val lK = 21.42191389
    val bK = 39.82951944
	
    // Selisih Bujur
	
    val a = 360 - bK + bujurTempat - 360
	
    val h = Math.toDegrees(asin(sin(Math.toRadians(lintangTempat)) * sin(Math.toRadians(lK)) + cos(Math.toRadians(lintangTempat))  * cos(Math.toRadians(lK)) * cos(Math.toRadians(a))))
	
    // Azimuth U-B
    val aQ = Math.toDegrees(acos((sin(Math.toRadians(lK)) - sin(Math.toRadians(lintangTempat)) * sin(Math.toRadians(h))) / cos(Math.toRadians(lintangTempat)) / cos(Math.toRadians(h))))
	
	// Azimuth B-U
    val bU = 90 - aQ
	
	// Azimuth UTSB
	val p = 360 - aQ
	
	// Roshdul Qiblat
    val b = 90 - lintangTempat
	
	val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(p)))))
	
	val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))
	
    // Roshdul Qiblat 1
	
    val rQ = -(pR -cA) / 15 + 12
	
    // Roshdul Qiblat 1 TimeZone
    val rashdul1 = rQ - eq - (bujurTempat - (timeZone * 15)) / 15
	
	// Roshdul Qiblat 2
	
    val rq1 = -((pR + cA) / 15) + 12//% 24
	val rq2 = rq1.mod(24.0)
	
    // Roshdul Qiblat 2 TimeZone
    val rashdul2 = rq2 - eq - (bujurTempat - (timeZone * 15)) / 15
	
	
    
	
	
	
	
	/*
		fungsi-fungsi
	*/
	
	// Dzuhur WIB
	fun getDzuhur(): String {
		return convertToTime(dzuhurWib)
	}
	
	// Ashar WIB
	fun getAshar(): String {
		return convertToTime(asharWib)
	}
	
	// Maghrib WIB
	fun getMaghrib(): String {
		return convertToTime(maghribWib)
	}
	
	// Isya' WIB
	fun getIsya(): String {
		return convertToTime(isyaWib)
	}
	
	// Shubuh WIB
	fun getShubuh(): String {
		return convertToTime(shubuhWib)
	}
	
	// Imsak WIB
	fun getImsak(): String {
		return convertToTime(imsakWib)
	}
	
	// Thulu' WIB
	fun getThulu(): String {
		return convertToTime(thuluWib)
	}
	
	// Dluha WIB
	fun getDluha(): String {
		return convertToTime(dluhaWib)
	}
	
	// Tengah Malam WIB
	fun getTengahMalam(): String {
		return convertToTime(tengahMalamWib)
	}
	
	
	
	// fungsi getDeklinasi
	fun getDeklinasi(): String {
		return convertToDegree(dek)
	}
	
	// fungsi getEquationOfTime
	fun getEquationOfTime(): String {
		return convertToDegree(eq)
	}
	
	// fungsi Arah Qiblat B-U
	fun getQiblatBU(): String {
		return convertToDegree(bU)
	}
	
	// fungsi Arah Qiblat UTSB
	fun getQiblatUTSB(): String {
		return convertToDegree(p)
	}
	
	// fungsi Rashdul Qiblat 1
	fun getRashdul1(): String {
		return convertToTimeL(rashdul1)
	}
	
	// fungsi Rashdul Qiblat 2
	fun getRashdul2(): String {
		return convertToTimeL(rashdul2)
	}
}
