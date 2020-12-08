module.exports = {
    collectCoverage: false,
    preset: 'ts-jest',
    testEnvironment: 'enzyme',
    "setupFilesAfterEnv": [
      "jest-enzyme"
    ],
  };
  