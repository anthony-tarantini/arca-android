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
package com.arca.service.test.mock;

import android.content.Context;
import android.os.Message;

import com.arca.service.OperationHandler;
import com.arca.service.RequestExecutor;

public class TestOperationHandler extends OperationHandler {

	public TestOperationHandler(final Context context, final RequestExecutor executor) {
		super(context, executor);
	}

	@Override
	public boolean sendMessageAtTime(final Message msg, final long uptimeMillis) {
		handleMessage(msg);
		return true;
	}

}