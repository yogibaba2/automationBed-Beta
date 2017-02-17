/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package Ituple.automationBed_Beta.utility.webDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Manager for PhantomJs.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.4.0
 */
public class PhantomJsDriverManager extends BrowserManager {

	private static PhantomJsDriverManager instance;

	public PhantomJsDriverManager() {
	}

	public static synchronized PhantomJsDriverManager getInstance() {
		if (instance == null) {
			instance = new PhantomJsDriverManager();
		}
		return instance;
	}

	@Override
	public List<URL> getDrivers() throws Exception {
		return getDriversFromTaobao(getDriverUrl());
	}

	@Override
	protected String getExportParameter() {
		return WdmConfig.getString("wdm.phantomjsDriverExport");
	}

	@Override
	protected String getDriverVersion() {
		return WdmConfig.getString("wdm.phantomjsDriverVersion");
	}

	@Override
	protected URL getDriverUrl() throws MalformedURLException {
		return WdmConfig.getUrl("wdm.phantomjsDriverUrl");
	}

	@Override
	protected List<String> getDriverName() {
		return Arrays.asList("phantomjs");
	}

	@Override
	public String getCurrentVersion(URL url, String driverName)
			throws MalformedURLException {
		String file = url.getFile();
		file = url.getFile().substring(file.lastIndexOf(SEPARATOR),
				file.length());
		final int matchIndex = file.indexOf(driverName);
		String currentVersion = file
				.substring(matchIndex + driverName.length() + 1, file.length());
		final int dashIndex = currentVersion.indexOf('-');
		currentVersion = currentVersion.substring(0, dashIndex);
		return currentVersion;
	}
}
