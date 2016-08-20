package bespalhuk.infra.persistence;

import java.net.URI;
import java.net.URISyntaxException;

import static com.google.common.base.Preconditions.checkNotNull;

public class DatabaseProperties {

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private final String url;

	private final String username;

	private final String password;

	private DatabaseProperties(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DatabaseProperties of(String uri) {
		checkNotNull(uri);
		try {
			URI databaseUri = new URI(uri);
			String url = String.format("jdbc:mysql://%s:%d%s?useLegacyDatetimeCode=false&serverTimezone=UTC",
					databaseUri.getHost(), databaseUri.getPort(), databaseUri.getPath());
			String[] userInfo = databaseUri.getUserInfo().split(":");
			return new DatabaseProperties(url, userInfo[0], userInfo[1]);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("URI de conexão com o banco de dados mal formada.");
		}
	}

	public String getDriver() {
		return DRIVER;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
