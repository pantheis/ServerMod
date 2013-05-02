package com.matthewprenger.servermod.api.provider;

public interface PastebinProvider {

	public String paste(String title, String text) throws PasteException;
	
	public static class PasteException extends RuntimeException {

        private static final long serialVersionUID = 8559690389507011890L;

        public PasteException(String message) {
			super(message);
		}

		public PasteException(Throwable cause) {
			super(cause);
		}
	}
}
