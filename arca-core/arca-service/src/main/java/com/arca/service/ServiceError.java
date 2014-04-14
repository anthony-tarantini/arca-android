/* 
 * Copyright (C) 2014 Pivotal Software, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arca.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class ServiceError implements Parcelable {

	public static interface Codes {
		public static final int UNKNOWN = 100;
	}

	public static interface Messages {
		public static final String UNKNOWN = "An unknown error occured.";
	}

	private final int mCode;
	private final String mMessage;
	private final String mType;

	public ServiceError(final int code, final String type, final String message) {
		mCode = code;
		mMessage = message;
		mType = type;
	}

	public ServiceError(final int code, final String message) {
		this(code, null, message);
	}

	public ServiceError(final String message) {
		this(Codes.UNKNOWN, message);
	}

	public ServiceError(final Exception exception) {
		this(exception.getLocalizedMessage());
	}

	public ServiceError(final Parcel in) {
		mCode = in.readInt();
		mMessage = in.readString();
		mType = in.readString();
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(mCode);
		dest.writeString(mMessage);
		dest.writeString(mType);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getMessage() {
		return mMessage;
	}

	public int getCode() {
		return mCode;
	}

	public String getType() {
		return mType;
	}

	@Override
	public String toString() {
		return String.format(Locale.getDefault(), "[%d] %s", mCode, mMessage);
	}

	public static final Creator<ServiceError> CREATOR = new Creator<ServiceError>() {
		@Override
		public ServiceError createFromParcel(final Parcel in) {
			return new ServiceError(in);
		}

		@Override
		public ServiceError[] newArray(final int size) {
			return new ServiceError[size];
		}
	};

}