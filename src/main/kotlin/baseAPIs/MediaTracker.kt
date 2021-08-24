package baseAPIs

import java.awt.Image
import java.util.*
import javax.sound.sampled.Clip

/**
 *  A Static object that keeps track of all cached images and sound.
 */
object MediaTracker {
    var cacheBmp = Hashtable<String, Image>()
    var cacheSound = Hashtable<String, Clip>()
    var REMOVE_EXISTING = true

    fun cacheImage(key: String, value: Image) {
        if (!hasBmpKey(key)) {
            cacheBmp[key] = value
        } else {
            if (REMOVE_EXISTING) {
                removeBmpByKey(key)
            }
            cacheBmp[key] = value
        }
    }

    fun cacheSound(key: String, value: Clip) {
        if (!hasSoundKey(key)) {
            cacheSound[key] = value
        } else {
            if (REMOVE_EXISTING) {
                removeSoundByKey(key)
            }
            cacheSound[key] = value
        }
    }

    fun getBmpCacheSize(): Int {
        return this.cacheBmp.size
    }

    fun getSoundCacheSize(): Int {
        return cacheSound.size
    }

    fun getBmpValue(key: String): Image? {
        return if (this.hasBmpKey(key)) {
            cacheBmp[key]
        } else {
            null
        }
    }

    fun getSoundValue(key: String): Clip? {
        return if (this.hasSoundKey(key)) {
            this.cacheSound[key]
        } else {
            null
        }
    }

    fun hasBmpKey(key: String): Boolean {
        return cacheBmp.containsKey(key)
    }


    fun hasSoundKey(key: String): Boolean {
        return cacheSound.containsKey(key)
    }


    fun hasBmpValue(img: Image): Boolean {
        return cacheBmp.containsValue(img)
    }

    fun hasSoundValue(snd: Clip): Boolean {
        return cacheSound.containsValue(snd)
    }

    fun removeBmpByKey(key: String): Boolean {
        return if (this.hasBmpKey(key)) {
            this.cacheBmp.remove(key)
            true
        } else {
            false
        }
    }

    fun removeSoundByKey(key: String): Boolean {
        return if (this.hasSoundKey(key)) {
            cacheSound.remove(key)
            true
        } else {
            false
        }
    }

    fun removeBmpByKeyValue(key: String, img: Image): Boolean {
        return if (this.hasBmpKey(key)) {
            cacheBmp.remove(key, img)
            true
        } else {
            false
        }
    }

    fun removeSoundByKeyValue(key: String, snd: Clip): Boolean {
        return if (this.hasSoundKey(key)) {
            cacheSound.remove(key, snd)
            true
        } else {
            false
        }
    }
}