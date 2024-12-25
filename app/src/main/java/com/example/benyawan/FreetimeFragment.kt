package com.example.benyawan

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FreetimeFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_freetime, container, false)

        // เชื่อมปุ่มทั้งหมด
        val btnPlayPause1 = view.findViewById<Button>(R.id.btnPlayPause1)
        val btnPlayPause2 = view.findViewById<Button>(R.id.btnPlayPause2)
        val btnPlayPause3 = view.findViewById<Button>(R.id.btnPlayPause3)
        val btnPlayPause4 = view.findViewById<Button>(R.id.btnPlayPause4)
        val btnPlayPause5 = view.findViewById<Button>(R.id.btnPlayPause5)
        val btnPlayPause6 = view.findViewById<Button>(R.id.btnPlayPause6)

        // ตั้งค่าการคลิกให้กับทุกปุ่ม
        btnPlayPause1.setOnClickListener { handlePlayPause(it as Button, R.raw.gone) } //คลิกปุ่ม Play/pause แล้วจะเล่น/ปิดเพลง gone
        btnPlayPause2.setOnClickListener { handlePlayPause(it as Button, R.raw.firstlove) }
        btnPlayPause3.setOnClickListener { handlePlayPause(it as Button, R.raw.flowers) }
        btnPlayPause4.setOnClickListener { handlePlayPause(it as Button, R.raw.solo) }
        btnPlayPause5.setOnClickListener { handlePlayPause(it as Button, R.raw.rockstar) }
        btnPlayPause6.setOnClickListener { handlePlayPause(it as Button, R.raw.ontheground) }

        return view
    }

    private fun handlePlayPause(button: Button, musicResId: Int) {
        // หากมีเพลงกำลังเล่นอยู่และกดปุ่มใหม่ ให้หยุดเพลงก่อนหน้า
        if (currentPlayingButton != null && currentPlayingButton != button) {
            stopMusic(currentPlayingButton!!)
        }

        // ตรวจสอบว่า MediaPlayer กำลังเล่นเพลงนี้หรือไม่
        if (mediaPlayer != null && mediaPlayer?.isPlaying == true && currentPlayingButton == button) {
            // กดปุ่มเดิม ให้หยุดเพลง
            stopMusic(button)
        } else {
            // กดปุ่มใหม่ ให้เล่นเพลง
            playMusic(button, musicResId)
        }
    }

    private fun playMusic(button: Button, musicResId: Int) {
        // หยุด MediaPlayer เดิมก่อนสร้างใหม่
        mediaPlayer?.release()

        // สร้าง MediaPlayer ใหม่
        mediaPlayer = MediaPlayer.create(requireContext(), musicResId)
        mediaPlayer?.start()

        //เมื่อกดปุ่ม play จะเปลี่ยนข้อความบนปุ่มเป็น pause
        currentPlayingButton = button
        button.text = "Pause"
    }

    private fun stopMusic(button: Button) { //เมื่อหยุดเพลงปุ่มจะเปลี่ยนเป็นคำว่า play
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        // อัปเดตสถานะปุ่ม
        button.text = "Play"
        currentPlayingButton = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}