/**
 * Copyright (c) 2013, Sana
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Sana nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL Sana BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF 
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sana.android.content.core;

import java.text.ParseException;

import org.sana.core.Procedure;
import org.sana.util.DateUtil;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable implementation of {@link org.sana.core.Procedure}.
 * 
 * @author Sana Development
 *
 */
public class ProcedureParcel extends Procedure implements Parcelable {
	public static final String TAG = ProcedureParcel.class.getSimpleName();

	/**
	 * Constructs a new instance from a {@link android.os.Parcel Parcel}.
	 * @param in The {@link android.os.Parcel} to read from.
	 */
	public ProcedureParcel(Parcel in){
		setUuid(in.readString());
		try {
			setCreated(DateUtil.parseDate(in.readString()));
			setModified(DateUtil.parseDate(in.readString()));
		} catch (ParseException e) {			
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		//TODO Complete
	}
	
	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getUuid());
		dest.writeString(DateUtil.format(getCreated()));
		dest.writeString(DateUtil.format(getModified()));
		//TODO Complete this
	}
	

	/* (non-Javadoc)
	 * @see android.os.Parcelable.Creator
	 */
	public static final Parcelable.Creator<ProcedureParcel> CREATOR = 
			new Parcelable.Creator<ProcedureParcel>(){

				@Override
				public ProcedureParcel createFromParcel(Parcel source) {
					return new ProcedureParcel(source);
				}

				@Override
				public ProcedureParcel[] newArray(int size) {
					return new ProcedureParcel[size];
				}
		
	};
}
