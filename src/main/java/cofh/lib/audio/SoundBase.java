package cofh.lib.audio;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Generic ISound class with lots of constructor functionality. Required because - of course - Mojang has no generic that lets you specify *any* arguments for
 * this.
 *
 * @author skyboy
 *
 */
@SideOnly(Side.CLIENT)
public class SoundBase implements ISound {

	protected Sound sound;
	protected AttenuationType attenuation;
	@Nullable
	private SoundEventAccessor soundEvent;
	protected SoundCategory category;
	protected final ResourceLocation soundLocation;
	protected float volume;
	protected float pitch;
	protected float x;
	protected float y;
	protected float z;
	protected boolean repeat;
	protected int repeatDelay;

	public SoundBase(String sound) {

		this(sound, 0);
	}

	public SoundBase(String sound, float volume) {
		this(sound, volume, 0);
	}

	public SoundBase(String sound, float volume, float pitch) {

		this(sound, volume, pitch, false, 0);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay) {

		this(sound, volume, pitch, repeat, repeatDelay, 0, 0, 0, AttenuationType.NONE);
	}

	public SoundBase(String sound, float volume, float pitch, double x, double y, double z) {

		this(sound, volume, pitch, false, 0, x, y, z);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(sound, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z, AttenuationType attenuation) {

		this(new ResourceLocation(sound),SoundCategory.MASTER, volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
	}

	public SoundBase(ResourceLocation sound) {

		this(sound, 0);
	}

	public SoundBase(ResourceLocation sound, float volume) {

		this(sound, volume, 0);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch) {

		this(sound, volume, pitch, false, 0);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay) {

		this(sound,SoundCategory.MASTER, volume, pitch, repeat, repeatDelay, 0, 0, 0, AttenuationType.NONE);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, double x, double y, double z) {

		this(sound, volume, pitch, false, 0, x, y, z);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(sound, SoundCategory.MASTER, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z,
			AttenuationType attenuation) {

		this.attenuation = attenuation;
		this.soundLocation = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.category = category;
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
		this.repeat = repeat;
		this.repeatDelay = repeatDelay;
	}

	public SoundBase(SoundBase other) {

		this.attenuation = other.attenuation;
		this.sound = other.sound;
		this.soundLocation = other.soundLocation;
		this.category = other.category;
		this.volume = other.volume;
		this.pitch = other.pitch;
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		this.repeat = other.repeat;
		this.repeatDelay = other.repeatDelay;
	}

	@Override
	public AttenuationType getAttenuationType() {

		return attenuation;
	}

	@Override
	public ResourceLocation getSoundLocation() {

		return soundLocation;
	}

	@Nullable
	@Override
	public SoundEventAccessor createAccessor(SoundHandler handler) {
		this.soundEvent = handler.getAccessor(this.soundLocation);

		if (this.soundEvent == null)
		{
			this.sound = SoundHandler.MISSING_SOUND;
		}
		else
		{
			this.sound = this.soundEvent.cloneEntry();
		}

		return this.soundEvent;
	}

	@Override
	public Sound getSound() {
		return sound;
	}

	@Override
	public SoundCategory getCategory() {
		return category;
	}

	@Override
	public float getVolume() {

		return volume;
	}

	@Override
	public float getPitch() {

		return pitch;
	}

	@Override
	public float getXPosF() {

		return x;
	}

	@Override
	public float getYPosF() {

		return y;
	}

	@Override
	public float getZPosF() {

		return z;
	}

	@Override
	public boolean canRepeat() {

		return repeat;
	}

	@Override
	public int getRepeatDelay() {

		return repeatDelay;
	}

}
